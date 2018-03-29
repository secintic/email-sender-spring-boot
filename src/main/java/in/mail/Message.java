package in.mail;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
class Message {
    private String to;
    private String[] cc;
    private String body;
    private String subject;
}
