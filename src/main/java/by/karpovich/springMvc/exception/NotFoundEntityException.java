package by.karpovich.springMvc.exception;

public class NotFoundEntityException extends RuntimeException {
    public NotFoundEntityException(Throwable throwable) {
        super(throwable);
    }

    public NotFoundEntityException(String message) {
        super(message);
    }
}
