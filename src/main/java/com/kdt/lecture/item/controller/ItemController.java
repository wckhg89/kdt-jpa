package com.kdt.lecture.item.controller;

import com.kdt.lecture.ApiResponse;
import com.kdt.lecture.item.dto.ItemDto;
import com.kdt.lecture.item.service.ItemService;
import javassist.NotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
public class ItemController {

    @ExceptionHandler
    private ApiResponse<String> exceptionHandle(Exception exception) {
        return ApiResponse.fail(HttpStatus.INTERNAL_SERVER_ERROR.value(), exception.getMessage());
    }

    @ExceptionHandler(NotFoundException.class)
    private ApiResponse<String> notFoundHandle(NotFoundException exception) {
        return ApiResponse.fail(HttpStatus.NOT_FOUND.value(), exception.getMessage());
    }

    private final ItemService itemService;

    @PostMapping("/items")
    public ApiResponse<Long> save(
            @RequestBody ItemDto itemDto
    ) {
        Long id = itemService.save(itemDto);
        return ApiResponse.ok(id);
    }

    @GetMapping("/items/{id}")
    public ApiResponse<ItemDto> getItem(
            @PathVariable Long id
    ) throws NotFoundException {
        ItemDto item = itemService.getItem(id);
        return ApiResponse.ok(item);
    }
}
