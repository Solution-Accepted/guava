package com.google.common.hash;

import junit.framework.TestCase;

import java.nio.ByteBuffer;

/**
 * Tests for AbstractHasher.
 *
 * @author Junxian Chen
 */
public class AbstractHasherTest extends TestCase {

    private class MyHasher extends AbstractHasher {
        private byte[] bytes = new byte[0];

        @Override
        public Hasher putByte(byte b) {
            byte[] bytesOld = bytes;
            byte[] bytesNew = new byte[bytesOld.length + 1];
            System.arraycopy(bytesOld, 0, bytesNew, 0, bytesOld.length);
            bytesNew[bytesOld.length] = b;
            bytes = bytesNew;
            return this;
        }

        @Override
        public HashCode hash() {
            return HashCode.fromBytes(bytes);
        }

        public byte[] getBytes() {
            return bytes;
        }
    }

    public void testPutBytesWithPrimitiveByteArray() {
        MyHasher hasher = new MyHasher();
        byte[] expected = new byte[]{0, 1, 2};
        hasher.putBytes(expected, 0, 3);
        assertEquals(expected.length, hasher.getBytes().length);
    }

    public void testPutBytesWithByteBufferThatHasArray() {
        MyHasher hasher = new MyHasher();
        byte[] expected = new byte[]{0, 1, 2};
        ByteBuffer buffer = ByteBuffer.wrap(expected);
        hasher.putBytes(buffer); // backed by array
        assertEquals(buffer.array().length, hasher.getBytes().length);
    }

    public void testPutBytesWithByteBufferThatNotHasArray() {
        MyHasher hasher = new MyHasher();
        ByteBuffer buffer = ByteBuffer.allocate(3);
        buffer.put((byte) 0);
        buffer.put((byte) 1);
        buffer.put((byte) 2);
        buffer.rewind();
        hasher.putBytes(buffer.asReadOnlyBuffer()); // not backed by array
        assertEquals(buffer.array().length, hasher.getBytes().length);
    }

    public void testCallingHash() {
        new MyHasher().putByte((byte) 0).hash();
    }
}
