import java.lang.reflect.Array;

public class NestedArrayUtil {

    // 두 중첩 배열이 동일한지 비교하는 메서드
    public static boolean compareNestedArrays(Object array1, Object array2) {
        // 두 객체가 둘 다 배열인지 확인
        if (!array1.getClass().isArray() || !array2.getClass().isArray()) {
            return false;
        }

        // 배열의 길이가 다르면 false 반환
        int length1 = Array.getLength(array1);
        int length2 = Array.getLength(array2);
        if (length1 != length2) {
            return false;
        }

        // 각 요소를 비교
        for (int i = 0; i < length1; i++) {
            Object element1 = Array.get(array1, i);
            Object element2 = Array.get(array2, i);

            if (element1.getClass().isArray() && element2.getClass().isArray()) {
                // 재귀적으로 서브 배열 비교
                if (!compareNestedArrays(element1, element2)) {
                    return false;
                }
            } else if (!element1.equals(element2)) {
                return false;
            }
        }

        // 모든 요소가 동일하면 true 반환
        return true;
    }

    public static boolean isNestedArray(Object obj) {
        // 객체가 null이면 중첩 배열이 아님
        if (obj == null) {
            return false;
        }

        // 객체가 배열인지 확인
        if (obj.getClass().isArray()) {
            // 배열의 첫 번째 요소가 배열인지 확인
            int length = Array.getLength(obj);
            if (length == 0) {
                return false; // 빈 배열이면 중첩 배열 아님
            }

            Object firstElement = Array.get(obj, 0);
            return firstElement != null && firstElement.getClass().isArray();
        }

        return false;
    }

}
