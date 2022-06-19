package br.com.notification.controller;

import br.com.notification.config.Constants;
import br.com.notification.exception.CpfExistsException;
import br.com.notification.request.NotificationRequest;
import br.com.notification.response.NotificationResponse;
import br.com.notification.service.NotificationService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

@RestController
@RequestMapping(value = Constants.NOTIFICATION_API)
@Tag(name = "API to manager customs", description = "create and manager customers")
@Slf4j

public class NotificationController {

        private NotificationService notificationService;

        public NotificationController(NotificationService notificationService) {
            this.notificationService = notificationService;
        }


        @GetMapping
        @Operation(summary = "Get Notifications", description = "list all notifications. Page must be send in request params as pageNumber, paseSize and sortBy")
        @ApiResponse(responseCode = "200", description = "notification list sucessfully")
        public ResponseEntity<List<NotificationResponse>> getNotifications(
                @RequestParam(defaultValue = "0") Integer pageNumber,
                @RequestParam(defaultValue = "10") Integer pageSize,
                @RequestParam(defaultValue = "id") String sortBy
        ) {
            DateTimeFormatter formatter  = DateTimeFormatter.ofPattern(Constants.DATE_TIME_DEFAULT_PATTERN);
            log.info("listing all notifications at time: "+ LocalDateTime.now().format(formatter));
            List<NotificationResponse> fraudResponse = this.notificationService.getNotification(pageNumber, pageSize, sortBy);
            if (fraudResponse == null) {
                return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
            }
            return new ResponseEntity<List<NotificationResponse>>(fraudResponse, HttpStatus.OK);
        }

        @PostMapping
        @Operation(summary = "notification customers", description = "create new notification ")
        @ApiResponse(responseCode = "201", description = "notification sucessfully created")
        public ResponseEntity<?> createCustomer(@RequestBody NotificationRequest notificationRequest) {
            log.info("calling notification service to create: {}", notificationRequest);
            this.notificationService.saveNotification(notificationRequest);
            return ResponseEntity.status(HttpStatus.CREATED).build();
        }

}
