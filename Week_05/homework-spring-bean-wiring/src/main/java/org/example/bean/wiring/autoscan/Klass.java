package org.example.bean.wiring.autoscan;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@ToString
@Data
@NoArgsConstructor
@AllArgsConstructor
@Slf4j
public class Klass {
    private String klassName;
    private List<Student> students;
}
