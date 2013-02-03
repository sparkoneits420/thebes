/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.thebes.net.io;

import java.nio.ByteBuffer;
import java.util.ArrayList;

/** 
 *
 * @author smokey
 */
public class PacketBuilder extends Packet {
    
    public int bitIndex = 0;
    public static int BIT_PLACES[] = new int[32];
    private static final int packetIndex = 10;
    private int packetBufferPtr = -1;
    private int packetBuffer[] = new int[packetIndex];
    public ByteBuffer packet;

    public PacketBuilder(ByteBuffer buffer) {
        super(buffer); 
    }

    public void addByteA(byte b) { 
        addByte((byte)(b + 128));
    }

    public void addByteS(byte b) { 
        addByte((byte)(128 - b));
    }

    public void addByteC(byte b) { 
        addByte((byte)-b);
    }

    public void addLEShortA(short s) {
        addByte((byte) (s + 128));
        addByte((byte) (s >> 8));
    }

    public void addShortA(short s) {
        addByte((byte) (s >> 8));
        addByte((byte) (s + 128));
                
    }

    public void addLEShort(short s) {
        addByte((byte) s);
        addByte((byte) (s >> 8));
    }

    public void addIntVA(int i) {
        addByte((byte) (i >> 8));
        addByte((byte) i);
        addByte((byte) (i >> 24));
        addByte((byte) (i >> 16));
    }

    public void addIntVB(int i) {
        addByte((byte) (i >> 16));
        addByte((byte) (i >> 24));
        addByte((byte) i);
        addByte((byte) (i >> 8));
    }

    public void addBytesReverse(byte bytes[], int i, int j) {
        for (int k = (j + i) - 1; k >= j; k--) {
            addByte(bytes[k]);
        }

    }

    public void addBytesReverseA(byte bytes[], int i, int j) {
        for (int k = (j + i) - 1; k >= j; k--) {
            addByte((byte) (bytes[k] + 128));
        }

    }

    public void addByte(byte b) {
        buffer.put((byte) b);
    }

    public void addShort(short s) {
        addByte((byte) (s >> 8));
        addByte((byte) s);
    }

    public void addTriByte(int i) {
        addByte((byte) (i >> 16));
        addByte((byte) (i >> 8));
        addByte((byte) i);
    }

    public void addInt(int i) {
        addByte((byte) (i >> 24));
        addByte((byte) (i >> 16));
        addByte((byte) (i >> 8));
        addByte((byte) i);
    }

    public void addLEInt(int i) {
        addByte((byte) i);
        addByte((byte) (i >> 8));
        addByte((byte) (i >> 16));
        addByte((byte) (i >> 24));
    }

    public void addLong(long l) {
        addByte((byte) (l >> 56));
        addByte((byte) (l >> 48));
        addByte((byte) (l >> 40));
        addByte((byte) (l >> 32));
        addByte((byte) (l >> 24));
        addByte((byte) (l >> 16));
        addByte((byte) (l >> 8));
        addByte((byte)l);
    }

    public void addString(String s) {
        for (char c : s.toCharArray()) {
            addByte((byte) c);
        }
        addByte((byte)10);
    }

    public void addBytes(byte bytes[], int length, int offset) {
        for (int i = offset; i < offset + length; i++) {
            addByte(bytes[i]);
        }
    }

    public void addBytes(byte[] bytes) {
        for (byte b : bytes) {
            addByte(b);
        }
    }

    public void createFrame(int id, int secure) { 
        addByte((byte) (id + secure));
    }

    public void createFrameVarSize(int id, int secure) {
        addByte((byte) (id + secure));
        addByte((byte)0); // placeholder for size byte
        if (packetBufferPtr >= packetIndex - 1) {
            throw new RuntimeException("Stack overflow");
        } else {
            packetBuffer[++packetBufferPtr] = buffer.position();
        }
    }

    public void createFrameVarSizeWord(int id, int secure) {
        addByte((byte) (id + secure));
        addShort((short)0); // placeholder for size word
        if (packetBufferPtr >= packetIndex - 1) {
            throw new RuntimeException("Stack overflow");
        } else {
            packetBuffer[++packetBufferPtr] = buffer.position();
        }
    }

    public void endFrameVarSize() {
        if (packetBufferPtr < 0) {
            throw new RuntimeException("Stack empty");
        } else {
            addFrameSize(buffer.position() - packetBuffer[packetBufferPtr--]);
        }
    }

    public void endFrameVarSizeWord() {
        if (packetBufferPtr < 0) {
            throw new RuntimeException("Stack empty");
        } else {
            addFrameSizeWord(buffer.position() - packetBuffer[packetBufferPtr--]);
        }
    }

    public void addFrameSize(int i) {
        int j = buffer.position(); //i would assume the position needs to be set back(then again i think this is the last byte sent?)
        buffer.position(buffer.position() - i - 1);
        addByte((byte) i);
        buffer.position(j);
    }
 
    public void addFrameSizeWord(int i) {
        int j = buffer.position();
        buffer.position(buffer.position() - i - 2);
        addByte((byte) (i >> 8));
        addByte((byte) i);
        buffer.position(j);
    }

    public void initBitAccess() {
        bitIndex = buffer.position() * 8;
    }
 
    public void addBits(int numBits, int value) {
        int bytePos = bitIndex >> 3;
        int bitOffset = 8 - (bitIndex & 7);
        bitIndex += numBits;
        ArrayList<Bit> bits = new ArrayList<>(numBits / 8);
        for (; numBits > bitOffset; bitOffset = 8) {
            bits.add(new Bit(buffer.array()[bytePos] &= ~BIT_PLACES[bitOffset],
                    bytePos)); 
            bits.add(new Bit(buffer.array()[bytePos++] |= (value >> 
                    (numBits - bitOffset)) & BIT_PLACES[bitOffset], bytePos));

            numBits -= bitOffset;
        }
        if (numBits == bitOffset) {
            bits.add(new Bit(buffer.array()[bytePos] &= ~BIT_PLACES[bitOffset],
                    bytePos));
            bits.add(new Bit(buffer.array()[bytePos] |= value & 
                    BIT_PLACES[bitOffset], bytePos)); 
        } else {
            buffer.array()[bytePos] &= ~(BIT_PLACES[numBits]
                    << (bitOffset - numBits));
            buffer.array()[bytePos] |= (value & BIT_PLACES[numBits]) 
                    << (bitOffset - numBits);
        }
        for(Bit bit : bits) {
            buffer.position(bit.index);
            addByte(bit.value);  
        } 
    }

    public void finishBitAccess() {
        buffer.position((bitIndex + 7) / 8);
    }

    static {
        for (int i = 0; i < 32; i++) {
            BIT_PLACES[i] = (1 << i) - 1;
        }
    }
    
    
    public class Bit {
        public byte value;
        public int index;
        public Bit(byte value, int index) {
            this.value = value;
            this.index = index;
        }
    }
}
