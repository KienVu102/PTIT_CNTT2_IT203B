package exceptions;

public class InsufficientStockException extends RuntimeException {
    private static final long serialVersionUID = 1L;

    public InsufficientStockException() {
        super();
    }

    public InsufficientStockException(String message) {
        super(message);
    }

    public InsufficientStockException(String itemName, int requestedQuantity) {
        super(String.format("'%s' hết hàng: đã đặt %d nhưng không còn sản phẩm nào",
                itemName, requestedQuantity));
    }
}