import org.springframework.cloud.contract.spec.Contract
import org.springframework.http.HttpStatus

Contract.make {

    description "should return all Reservations"
    request {
        method(GET())
        url("/reservations")

    }
    response {

        headers {
            contentType(applicationJsonUtf8())
        }
        body(
                """
                [ 
                {
                    "id": 1, "name" : "A"
                } , 
                {
                    "id" : 2 , "name" : "B"
                } 
                ] 
        """)
        status(HttpStatus.OK.value())
    }

}