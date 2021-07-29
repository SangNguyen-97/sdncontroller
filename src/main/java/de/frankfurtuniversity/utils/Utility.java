package de.frankfurtuniversity.utils;

import java.nio.ByteBuffer;

public class Utility {
    // /**
    //  * Return long value of this 8-bytes sub-array from "offset" (big-endian)
    //  * 
    //  * @param raw    Byte array respresenting the value to be returned in long in
    //  *               big-endian order
    //  * @param length number of bytes to be used for the return value. Must be equal or less than 8
    //  * @return long value representation of the given byte array
    //  */
    // public static long bytesToLong(byte[] raw, int offset, int length) {
    //     ByteBuffer b = ByteBuffer.allocate(Long.BYTES);

    //     if (raw.length <= Long.BYTES){

    //     } else {
    //         if (length < Long.BYTES) {
    //             byte[] temp = Arrays.copyOfRange(raw, offset, offset + length);
    //             for (int i=0;i<Long.BYTES-length;i++){
    //                 b.put((byte)0);
    //             }
    //             b.put(temp);
    //             b.flip();
    //             return b.getLong();
    //         } else {
    //             return 0;
    //         }
    //     }
    // }

    /**
     * Return integer value of this 8-bytes sub-array from "offset" (big-endian)
     * 
     * @param raw    Byte array respresenting the value to be returned in integer in
     *               big-endian order
     * @param offset offset of the input raw array
     * @return integer value representation of the given byte array
     */
    public static int bytesToInt(byte[] raw, int offset) {
        ByteBuffer b = ByteBuffer.allocate(Integer.BYTES);
        if (raw.length <= Integer.BYTES) {
            int pad = Integer.BYTES - raw.length;
            for (int i = 0; i < pad; i++) {
                b.put((byte) 0);
            }
            b.put(raw);
        } else {
            b.put(raw, offset, Integer.BYTES);
        }
        b.flip();
        return b.getInt();
    }

    /**
     * Return short value of 8-bytes sub-array from "offset" (big-endian)
     * 
     * @param raw    Byte array respresenting the value to be returned in short in
     *               big-endian order
     * @param offset offset of the input raw array
     * @return short value representation of the given byte array
     */
    public static short bytesToShort(byte[] raw, int offset) {
        ByteBuffer b = ByteBuffer.allocate(Short.BYTES);
        if (raw.length <= Short.BYTES) {
            int pad = Short.BYTES - raw.length;
            for (int i = 0; i < pad; i++) {
                b.put((byte) 0);
            }
            b.put(raw);
        } else {
            b.put(raw, offset, Short.BYTES);
        }
        b.flip();
        return b.getShort();
    }

    /**
     * Return byte array in big-endian order representing this long value]
     * 
     * @param l long value
     * @return big-endian byte array
     */
    public static byte[] longToBytes(long l) {
        ByteBuffer b = ByteBuffer.allocate(Long.BYTES);
        b.putLong(l);
        b.flip();
        return b.array();
    }

    /**
     * Return byte array in big-endian order representing this integer value
     * 
     * @param l integer value
     * @return big-endian byte array
     */
    public static byte[] intToBytes(int i) {
        ByteBuffer b = ByteBuffer.allocate(Integer.BYTES);
        b.putInt(i);
        return b.array();
    }

    /**
     * Return a byte array created from the input bytes. First byte argument is the
     * most significant byte
     * 
     * @param bs byte value
     * @return byte array
     */
    public static byte[] byteToBytes(byte... bs) {
        ByteBuffer buff = ByteBuffer.allocate(bs.length);
        for (byte b : bs) {
            buff.put(b);
        }
        return buff.array();
    }
}
