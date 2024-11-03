package java15.models;

import lombok.*;

@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class Job {
    private Long id;
    private String position;
    private String description;
    private int experience;

    public Job(String position, String description, int experience) {
        this.position = position;
        this.description = description;
        this.experience = experience;
    }
}
