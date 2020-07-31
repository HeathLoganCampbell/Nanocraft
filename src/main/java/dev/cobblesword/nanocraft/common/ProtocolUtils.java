package dev.cobblesword.nanocraft.common;

import io.netty.buffer.ByteBuf;
import io.netty.buffer.ByteBufUtil;

import java.nio.charset.StandardCharsets;
import java.util.UUID;

import static com.google.common.base.Preconditions.checkArgument;
import static com.google.common.base.Preconditions.checkState;
/*
Class created by VelocityPowered
https://github.com/VelocityPowered/Velocity
 */
public class ProtocolUtils
{
    private static final int DEFAULT_MAX_STRING_SIZE = 65536; // 64KiB

    public static int readVarInt(ByteBuf buf) {
        int i = 0;
        int j = 0;
        while (true) {
            int k = buf.readByte();
            i |= (k & 0x7F) << j++ * 7;
            if (j > 5) {
                throw new RuntimeException("VarInt too big");
            }
            if ((k & 0x80) != 128) {
                break;
            }
        }
        return i;
    }

    public static void writeVarInt(ByteBuf buf, int value) {
        while (true) {
            if ((value & 0xFFFFFF80) == 0) {
                buf.writeByte(value);
                return;
            }

            buf.writeByte(value & 0x7F | 0x80);
            value >>>= 7;
        }
    }

    public static String readString(ByteBuf buf) {
        return readString(buf, DEFAULT_MAX_STRING_SIZE);
    }

    public static String readString(ByteBuf buf, int cap) {
        int length = readVarInt(buf);
        checkArgument(length >= 0, "Got a negative-length string (%s)", length);
        checkArgument(length <= cap * 4, "Bad string size (got %s, maximum is %s)", length, cap);
        checkState(buf.isReadable(length),
                "Trying to read a string that is too long (wanted %s, only have %s)", length,
                buf.readableBytes());
        String str = buf.toString(buf.readerIndex(), length, StandardCharsets.UTF_8);
        buf.skipBytes(length);
        checkState(str.length() <= cap, "Got a too-long string (got %s, max %s)",
                str.length(), cap);
        return str;
    }

    public static void writeString(ByteBuf buf, CharSequence str) {
        int size = ByteBufUtil.utf8Bytes(str);
        writeVarInt(buf, size);
        ByteBufUtil.writeUtf8(buf, str);
    }

    public static byte[] readByteArray(ByteBuf buf) {
        return readByteArray(buf, DEFAULT_MAX_STRING_SIZE);
    }

    public static byte[] readByteArray(ByteBuf buf, int cap) {
        int length = readVarInt(buf);
        checkArgument(length >= 0, "Got a negative-length array (%s)", length);
        checkArgument(length <= cap, "Bad array size (got %s, maximum is %s)", length, cap);
        checkState(buf.isReadable(length),
                "Trying to read an array that is too long (wanted %s, only have %s)", length,
                buf.readableBytes());
        byte[] array = new byte[length];
        buf.readBytes(array);
        return array;
    }

    public static void writeByteArray(ByteBuf buf, byte[] array) {
        writeVarInt(buf, array.length);
        buf.writeBytes(array);
    }

    public static int[] readIntegerArray(ByteBuf buf) {
        int len = readVarInt(buf);
        checkArgument(len >= 0, "Got a negative-length integer array (%s)", len);
        int[] array = new int[len];
        for (int i = 0; i < len; i++) {
            array[i] = readVarInt(buf);
        }
        return array;
    }

    public static UUID readUuid(ByteBuf buf) {
        long msb = buf.readLong();
        long lsb = buf.readLong();
        return new UUID(msb, lsb);
    }

    public static void writeUuid(ByteBuf buf, UUID uuid) {
        buf.writeLong(uuid.getMostSignificantBits());
        buf.writeLong(uuid.getLeastSignificantBits());
    }

    public static UUID readUuidIntArray(ByteBuf buf) {
        long msbHigh = (long) buf.readInt() << 32;
        long msbLow = (long) buf.readInt() & 0xFFFFFFFFL;
        long msb = msbHigh | msbLow;
        long lsbHigh = (long) buf.readInt() << 32;
        long lsbLow = (long) buf.readInt() & 0xFFFFFFFFL;
        long lsb = lsbHigh | lsbLow;
        return new UUID(msb, lsb);
    }

    public static void writeUuidIntArray(ByteBuf buf, UUID uuid) {
        buf.writeInt((int) (uuid.getMostSignificantBits() >> 32));
        buf.writeInt((int) uuid.getMostSignificantBits());
        buf.writeInt((int) (uuid.getLeastSignificantBits() >> 32));
        buf.writeInt((int) uuid.getLeastSignificantBits());
    }

    public static String[] readStringArray(ByteBuf buf) {
        int length = readVarInt(buf);
        String[] ret = new String[length];
        for (int i = 0; i < length; i++) {
            ret[i] = readString(buf);
        }
        return ret;
    }

    public static void writeStringArray(ByteBuf buf, String[] stringArray) {
        if (stringArray == null) {
            writeVarInt(buf, 0);
            return;
        }
        writeVarInt(buf, stringArray.length);
        for (int i = 0; i < stringArray.length; i++) {
            writeString(buf, stringArray[i]);
        }
    }
}
