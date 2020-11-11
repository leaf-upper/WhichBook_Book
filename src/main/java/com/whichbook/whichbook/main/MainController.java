package com.whichbook.whichbook.main;

import com.whichbook.whichbook.main.dto.DefaultSearchBookRequestDto;
import com.whichbook.whichbook.main.dto.DetailSearchBookRequestDto;
import lombok.RequiredArgsConstructor;
import org.springframework.http.*;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.util.Optional;


@RestController
@RequiredArgsConstructor
public class MainController {

    private final NaverApiService apiService;

    @GetMapping("/search")
    public ResponseEntity search(@Valid DefaultSearchBookRequestDto dto, Errors errors){

        if(errors.hasErrors()){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        }

        ResponseEntity response = apiService.search_default(dto);

        response = Optional
                .of(response)
                .filter((res) -> res.getStatusCode().equals(HttpStatus.OK))
                .orElse(ResponseEntity.status(HttpStatus.BAD_REQUEST).build());

        return response;
    }


    @GetMapping("/search_detail")
    public ResponseEntity search_detail(@Valid DetailSearchBookRequestDto dto, Errors errors){

        if(errors.hasErrors()){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        }

        ResponseEntity response = apiService.search_detail(dto);

        response = Optional
                .of(response)
                .filter((res) -> res.getStatusCode().equals(HttpStatus.OK))
                .orElse(ResponseEntity.status(HttpStatus.BAD_REQUEST).build());

        return response;
    }


}
