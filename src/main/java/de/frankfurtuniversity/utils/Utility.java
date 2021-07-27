package de.frankfurtuniversity.utils;

import java.nio.ByteBuffer;

import de.frankfurtuniversity.utils.exception.RawBytesTooFewException;

public class Utility {
    /**
     * Return long value of this 8-bytes array (big-endian)
     * 
     * @param raw Byte array respresenting the value to be returned in long in
     *            big-endian order
     * @return long value representation of the given byte array
     * @throws RawBytesTooFewException when input array is less than 8 bytes (size
     *                                 of long in bytes)
     */
    public static long bytesToLong(byte[] raw) throws RawBytesTooFewException {
        if (raw.length < Long.BYTES)
            throw new RawBytesTooFewException();
        ByteBuffer b = ByteBuffer.allocate(Long.BYTES);
        b.put(raw, 0, 8);
        b.flip();
        return b.getLong();
    }

    /**
     * Return integer value of this 8-bytes array (big-endian)
     * 
     * @param raw Byte array respresenting the value to be returned in integer in
     *            big-endian order
     * @return integer value representation of the given byte array
     * @throws RawBytesTooFewException when input array is less than 8 bytes (size
     *                                 of integer in bytes)
     */
    public static int bytesToInt(byte[] raw) throws RawBytesTooFewException {
        if (raw.length < Integer.BYTES)
            throw new RawBytesTooFewException();
        ByteBuffer b = ByteBuffer.allocate(Integer.BYTES);
        b.put(raw, 0, 4);
        b.flip();
        return b.getInt();
    }

    /**
     * Return byte array in big-endian order representing this long value]
     * @param l long value
     * @return big-endian byte array
     */
    public static byte[] longToBytes(long l){
        ByteBuffer b = ByteBuffer.allocate(Long.BYTES);
        b.putLong(l);
        b.flip();
        return b.array();
    }

    /**
    * Return byte array in big-endian order representing this integer value
    * @param l integer value
    * @return big-endian byte array
    */
    public static byte[] intToBytes(int i){
        ByteBuffer b = ByteBuffer.allocate(Integer.BYTES);
        b.putInt(i);
        return b.array();
    }

        /**
         * Return a byte array created from the input bytes. First byte argument is the most significant byte
         * @param bs byte value
         * @return byte array
         */
    public static byte[] byteToBytes(byte... bs){
        ByteBuffer buff = ByteBuffer.allocate(bs.length);
        for (byte b : bs){
            buff.put(b);
        }
        return buff.array();
    }
}
