package org.example.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
// not in use. Using Map<String, Object> is more flexible for handling query parameters.
public class QueueOperationsDTO {
    public long queueId;
    public String userId;
}
