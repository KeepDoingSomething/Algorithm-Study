import java.lang.reflect.Constructor;
import java.lang.reflect.Method;
import java.util.HashMap;

public class Programmers<R> implements Problem<Object[], R>{

    Object answer;

    public Problem<Object[], R> setAnswer(Object answer) {
        this.answer = answer;
        return this;
    }

    @Override
    public HashMap<String, Object[]> getInputCase() {
        HashMap<String, Object[]> inputCase = null;

        try {
            String testClassName = answer.getClass().getPackage().toString().split(" ")[1] + ".TestCase";
            Class<?> inputClass = Class.forName(testClassName);

            Constructor<?> constructor = inputClass.getConstructor();
            Object instance = constructor.newInstance();

            inputCase = (HashMap<String, Object[]>) inputClass
                    .getMethod("getInput")
                    .invoke(instance);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        return inputCase;
    }

    @Override
    public HashMap<String, R> getResultCase() {

        HashMap<String, R> resultCase = null;

        try {
            String testClassName = answer.getClass().getPackage().toString().split(" ")[1] + ".TestCase";
            Class<?> testClass = Class.forName(testClassName);

            Constructor<?> constructor = testClass.getConstructor();
            Object instance = constructor.newInstance();

            resultCase = (HashMap<String, R>) testClass
                    .getMethod("getResult")
                    .invoke(instance);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }

        return resultCase;
    }

    @Override
    public R solve(Object[] parameter) throws Exception {
        Method[] methods = answer.getClass().getDeclaredMethods();

        // Solution class 변수 초기화를 위해 solve 마다 새로운 instance 생성
        Constructor constructor = answer.getClass().getConstructor();
        Object instance = constructor.newInstance();

        R result = null;
        for(Method method : methods){
            if(method.getName().equals("solution")){
                result = (R) method.invoke(instance, parameter);
            }
        }
        return result;
    }

}
