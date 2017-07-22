package no.rogfk.sab.modell;

import lombok.Data;
import lombok.RequiredArgsConstructor;

@Data
@RequiredArgsConstructor
public class ResponseError {
    private final String message;
}
