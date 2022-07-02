package com.re.bi.itemstore.domain.item;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

import javax.persistence.EntityNotFoundException;

@ResponseStatus(value = HttpStatus.NOT_FOUND, reason = "Requested item does not exist.")
public class ItemNotFoundException extends EntityNotFoundException {
}
