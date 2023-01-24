package com.springboot.bbsrestful.utils;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public class CustomException extends Exception {
	private final int ERROR_CODE;
	private final String ERROR_MESSAGE;
}
