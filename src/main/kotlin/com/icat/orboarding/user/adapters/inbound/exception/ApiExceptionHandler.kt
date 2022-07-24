package com.icat.orboarding.user.adapters.inbound.exception

import com.icat.orboarding.user.adapters.inbound.dtos.response.ErrorBodyResponseDTO
import com.icat.orboarding.user.application.exceptions.CnpjAlreadyRegisteredException
import com.icat.orboarding.user.application.exceptions.CpfAlreadyRegisteredException
import com.icat.orboarding.user.application.exceptions.EmailAlreadyRegisteredException
import com.icat.orboarding.user.application.exceptions.UserNotFoundException
import org.apache.coyote.Response
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.context.MessageSource
import org.springframework.context.i18n.LocaleContextHolder
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.MethodArgumentNotValidException
import org.springframework.web.bind.annotation.ControllerAdvice
import org.springframework.web.bind.annotation.ExceptionHandler
import java.util.*

@ControllerAdvice
class ApiExceptionHandler {

    @Autowired private lateinit var message: MessageSource

    @ExceptionHandler(UserNotFoundException::class)
    fun handleUerNotFoundException(ex: UserNotFoundException): ResponseEntity<ErrorBodyResponseDTO> {
        val responseBody = ErrorBodyResponseDTO(
            status = HttpStatus.NOT_FOUND.value(),
            type = "User Not Found",
            detail = ex.message!!
        )
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(responseBody)
    }

    @ExceptionHandler(EmailAlreadyRegisteredException::class)
    fun handleEmailAlreadyRegisteredException(ex: EmailAlreadyRegisteredException): ResponseEntity<ErrorBodyResponseDTO>  {
        val responseBody = ErrorBodyResponseDTO(
            status = HttpStatus.BAD_REQUEST.value(),
            type = "Email already registered",
            detail = ex.message!!
        )
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(responseBody)
    }

    @ExceptionHandler(CpfAlreadyRegisteredException::class)
    fun handleCpfAlreadyRegisteredException(ex: CpfAlreadyRegisteredException): ResponseEntity<ErrorBodyResponseDTO>  {
        val responseBody = ErrorBodyResponseDTO(
            status = HttpStatus.BAD_REQUEST.value(),
            type = "CPF already registered",
            detail = ex.message!!
        )
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(responseBody)
    }

    @ExceptionHandler(CnpjAlreadyRegisteredException::class)
    fun handleCnpjAlreadyRegisteredException(ex: CnpjAlreadyRegisteredException): ResponseEntity<ErrorBodyResponseDTO>  {
        val responseBody = ErrorBodyResponseDTO(
            status = HttpStatus.BAD_REQUEST.value(),
            type = "CPF already registered",
            detail = ex.message!!
        )
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(responseBody)
    }

    @ExceptionHandler(Exception::class)
    fun handleGenericException(ex: Exception): ResponseEntity<ErrorBodyResponseDTO>  {
        val responseBody = ErrorBodyResponseDTO(
            status = HttpStatus.INTERNAL_SERVER_ERROR.value(),
            type = "Internal application error",
            detail = ex.message!!
        )
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(responseBody)
    }

    @ExceptionHandler(MethodArgumentNotValidException::class)
    fun handleMethodArgumentNotValidException(ex: MethodArgumentNotValidException): ResponseEntity<ErrorBodyResponseDTO> {
        val errors: Map<String, String> = ex.bindingResult.fieldErrors.associateBy({it.field}, {message.getMessage(it, LocaleContextHolder.getLocale())})
        val responseBody = ErrorBodyResponseDTO(
            status = HttpStatus.BAD_REQUEST.value(),
            type = "Request body has some field with invalid input",
            detail = errors
        )
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(responseBody)
    }
}