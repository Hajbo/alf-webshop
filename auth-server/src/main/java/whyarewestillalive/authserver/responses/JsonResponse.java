package whyarewestillalive.authserver.responses;

public class JsonResponse {
    public ResponseType status;
    public Object response;

    public JsonResponse(ResponseType status, Object response) {
        this.status = status;
        this.response = response;
    }
}
