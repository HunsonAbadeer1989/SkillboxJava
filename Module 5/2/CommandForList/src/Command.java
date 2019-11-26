public enum Command {

    LIST( "LIST"),
    ADD("ADD"),
    ADD_TO("ADD_TO"),
    EDIT_BUSINESS( "EDIT"),
    DELETE_BUSINESS( "DELETE");

    private final String message;

    Command(String message) {
        this.message = message;
    }

    public String message() {
        return this.message;
    }

}
