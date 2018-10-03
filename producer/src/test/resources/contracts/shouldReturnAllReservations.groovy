import org.springframework.cloud.contract.spec.Contract
import org.springframework.cloud.contract.spec.internal.HttpMethods
import org.springframework.http.HttpStatus

Contract.make {

    description("should return all reservations")
    request {
        url("/reservations")
        method(HttpMethods.HttpMethod.GET)
    }
    response {
        status(HttpStatus.OK.value())
        headers {
            contentType(applicationJsonUtf8())
        }
        body(
                """
                    [
                        {"id":"1", "reservationName": "Ai"},
                        {"id":"2", "reservationName": "Zhang Wei"}
                    ]
                """
        )
    }
}