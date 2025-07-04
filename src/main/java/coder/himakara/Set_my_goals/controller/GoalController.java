package coder.himakara.Set_my_goals.controller;

import coder.himakara.Set_my_goals.dto.GoalDto;
import coder.himakara.Set_my_goals.dto.response.GoalResponseDto;
import coder.himakara.Set_my_goals.service.GoalService;
import coder.himakara.Set_my_goals.util.StandardResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/v1/goals")
public class GoalController {
 private final GoalService goalService;

    public GoalController(GoalService goalService) {
        this.goalService = goalService;
    }

    @PostMapping("/add")
    public ResponseEntity<StandardResponse> createGoal(@RequestBody GoalDto goalDto) {
        GoalResponseDto createdGoal = goalService.createGoal(goalDto);
        return new ResponseEntity<>(
                new StandardResponse(201, "Success", createdGoal),
                HttpStatus.CREATED
        );
    }

    @GetMapping("/all")
    public ResponseEntity<StandardResponse> getAllGoals() {
        List<GoalResponseDto> goals = goalService.getAllGoals();
        return new ResponseEntity<>(
                new StandardResponse(200, "Success", goals),
                HttpStatus.OK
        );
    }

    @GetMapping("/{id}")
    public ResponseEntity<StandardResponse> getGoalById(@PathVariable("id") Long id) {
        GoalResponseDto selectedGoal = goalService.getGoalById(id);
        return new ResponseEntity<>(
                new StandardResponse(200, "Success", selectedGoal),
                HttpStatus.OK
        );
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<StandardResponse> deleteGoal(@PathVariable("id") Long id) {
        goalService.deleteGoal(id);
        return new ResponseEntity<>(
                new StandardResponse(200, "Goal deleted successfully", null),
                HttpStatus.OK
        );
    }

    @PatchMapping("/update/{id}")
    public ResponseEntity<StandardResponse> updateGoal(@PathVariable("id") Long id,
                                                       @RequestBody Map<String, Object> fields) {
        GoalResponseDto updatedGoal = goalService.updateGoal(id, fields);
        return new ResponseEntity<>(
                new StandardResponse(200, "Goal updated successfully", updatedGoal),
                HttpStatus.OK
        );
    }

    @PatchMapping("/{id}/status/to-progress")
    public ResponseEntity<StandardResponse> updateGoalStatusToProgress(@PathVariable("id") Long id) {
        GoalResponseDto updatedGoal = goalService.updateGoalStatusToProgress(id);
        return new ResponseEntity<>(
                new StandardResponse(200, "Goal status updated to IN_PROGRESS", updatedGoal),
                HttpStatus.OK
        );
    }
}
