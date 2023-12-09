package com.qsorderservice.models;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Represents an email.
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class EmailDetails {

    private String recipient;
    private String msgBody;
    private String subject;

}
