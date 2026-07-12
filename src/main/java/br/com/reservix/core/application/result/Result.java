package br.com.reservix.core.application.result;



import java.util.function.Function;

public class Result<T> {
    private final T value;
    private final String errorReason;
    private final boolean isSuccess;

    private Result(T value, String errorReason, boolean isSuccess) {
        this.value = value;
        this.errorReason = errorReason;
        this.isSuccess = isSuccess;
    }

    public static <T> Result<T> success(T value) {
        return new Result<>(value, null, true);
    }

    public static <T> Result<T> failure(String reason) {
        return new Result<>(null, reason, false);
    }

    public boolean isSuccess() { return isSuccess; }
    public T getValue() { return value; }
    public String getErrorReason() { return errorReason; }


    public <X extends Throwable> T orElseThrow(Function<String, X> exceptionFunction) throws X {
        if (!this.isSuccess) {
            throw exceptionFunction.apply(this.errorReason);
        }
        return this.value;
    }
}