/**
 * Author    : Kang San Ah
 * Date      : 2025.01.19(Sun)
 * Runtime   : 1 sec
 * Memory    : 256 MB
 * Algorithm : 구현
 */


import java.math.BigInteger;

public class 멀쩡한사각형 {
    public long solution(int w, int h) {
        int gcd = BigInteger.valueOf(w).gcd(BigInteger.valueOf(h)).intValue();
        return ((long) w * (long) h) - ((((long) w / gcd) + ((long) h / gcd) - 1) * gcd);
    }
}
