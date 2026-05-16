Alexis Manosca 1644633
Jeff Jia 1641408

cat <file> | java ByteToHex | java LZ78encode | java PackBits | java UnpackBits | java LZ78decode | java HexToByte > <result>
diff <file> <result> should return nothing as result is a compressed then decompressed version of <file>

<file> <result> are now the same file.

No partnership problems.