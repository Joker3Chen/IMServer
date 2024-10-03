package model;

import lombok.Data;
import lombok.Getter;
import lombok.experimental.Accessors;

import java.util.List;

/**
 * @author Joker3Chen
 * @date 2024/10/2 2:27
 */
@Getter
@Data
@Accessors
public class MessageModel {
    String message;
    String messageType;
    String messageLength;
    String sourceAddress;
    String destinationAddress;
    String middleAddress;
}
