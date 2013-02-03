
package org.thebes.net.io;

import java.nio.ByteBuffer;

/** 
 * Represents a buffered OP_get packet
 *
 * @author smokey
 */
public class Packet {
    
    /**
     * The array of bytes containing all information pertaining to the packet, 
     * otherwise known as a buffer
     */
    public ByteBuffer buffer;
     
    public Packet(int size) {
        buffer = ByteBuffer.allocate(size);
    }
    
    public Packet(ByteBuffer buffer) {
        this.buffer = buffer;
    } 
    
    public byte getByteA() {
        return (byte) (getByte() - 128);
    }

    public byte getByteC() {
        return (byte) (-getByte());
    }

    public byte getByteS() {
        return (byte) (128 - getByte());
    }

    public int getUnsignedByteA() {
        return getByte() - 128 & 0xff;
    }

    public int getUnsignedByteC() {
        return -getByte() & 0xff;
    }

    public int getUnsignedByteS() {
        return 128 - getByte() & 0xff;
    }

    public int getLEShort() { 
        int i = (getByte() & 0xff) 
                + ((getByte() & 0xff) << 8);
        if (i > 32767) {
            i -= 0x10000;
        }
        return i;
    }

    public int getShortA() { 
        int i = ((getByte() - 128 & 0xff)
                + (getByte() & 0xff) << 8); 
        if (i > 32767) {
            i -= 0x10000;
        }
        return i;
    }

    public int getLEShortA() { 
        int i = ((getByte() & 0xff) << 8) 
                + (getByte() - 128 & 0xff);
        if (i > 32767) {
            i -= 0x10000;
        }
        return i;
    }

    public int getUnsignedLEShort() { 
        return ((getByte() & 0xff) << 8) 
                + (getByte() & 0xff);
    }

    public int getUnsignedShortA() { 
        return ((getByte() - 128 & 0xff) 
                + (getByte() & 0xff) << 8);
    }

    public int getUnsignedLEShortA() { 
        return ((getByte() & 0xff) << 8) 
                + (getByte() - 128 & 0xff);
    }
    
    public int getIntVA() { 
        return (((getByte() & 0xff) << 16) 
                + (getByte() & 0xff) << 24) 
                + (getByte() & 0xff) 
                + ((getByte() & 0xff) << 8);
    }

    public int getIntVB() { 
        return (((getByte() & 0xff) << 8)
                + (getByte() & 0xff)
                + (getByte() & 0xff) << 24)
                + ((getByte() & 0xff) << 16);
    } 

    public void getBytesReverse(byte abyte0[], int i, int j) {
        for (int k = (j + i) - 1; k >= j; k--) {
            abyte0[k] = getByte();
        } 
    }
    
    public void getBytesReverseA(byte abyte0[], int i, int j) {
        for (int k = (j + i) - 1; k >= j; k--) {
            abyte0[k] = (byte) (getByte() - 128);
        }

    } 

    public int getUnsignedByte() {
        return getByte() & 0xff;
    }

    public byte getByte() {
        return buffer.get();
    }

    public int getUnsignedShort() { 
        return ((getByte() & 0xff) << 8)
                + (getByte() & 0xff);
    }

    public int getShort() { 
        int i = ((getByte() & 0xff) << 8) 
                + (getByte() & 0xff);
        if (i > 32767) {
            i -= 0x10000;
        }
        return i;
    }

    public int getInt() { 
        return ((getByte() & 0xff) << 24) 
                + ((getByte() & 0xff) << 16) 
                + ((getByte() & 0xff) << 8) 
                + (getByte() & 0xff);
    }

    public long getLong() {
        long l = (long) getInt() & 0xffffffffL;
        long l1 = (long) getInt() & 0xffffffffL;
        return (l << 32) + l1;
    }

    public String getString() {
        StringBuilder sb = new StringBuilder();
        char c;
        while ((c = (char) getByte()) != 10) {
            sb.append(c);
        }
        return sb.toString();
    }

    public void getBytes(byte bytes[], int i, int j) {
        for (int k = j; k < j + i; k++) {
            bytes[k] = getByte();
        } 
    }  
  
    public ByteBuffer getPayload() {
        return buffer;
    }  
    
    public int size() {
        return buffer.capacity();
    }
}
