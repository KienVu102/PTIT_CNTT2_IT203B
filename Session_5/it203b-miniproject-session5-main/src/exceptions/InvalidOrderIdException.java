
package exceptions;

import java.io.Serial;
import java.util.UUID;

public class InvalidOrderIdException extends RuntimeException {
    @Serial
    private static final long serialVersionUID = 1L;

    public InvalidOrderIdException() {
        super();
    }

    public InvalidOrderIdException(String message) {
        super(message);
    }

    public InvalidOrderIdException(UUID orderId) {
        super("ID đơn hàng không hợp lệ: " + orderId);
    }
}
