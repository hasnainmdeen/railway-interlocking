package railwayinterlocking.railwaydemo.rest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import railwayinterlocking.railwaydemo.model.dto.CheckConflictRequest;
import railwayinterlocking.railwaydemo.model.dto.CheckConflictResponse;
import railwayinterlocking.railwaydemo.service.PathFinderServiceImpl;

@RestController
@RequestMapping("/railway")
public class RailwayController {

    @Autowired
    private PathFinderServiceImpl pathFinderServiceImpl;

    @PostMapping("/check_conflicts")
    public ResponseEntity<CheckConflictResponse> checkConflicts(@RequestBody CheckConflictRequest request) {
        boolean isPathValid = pathFinderServiceImpl.checkConflicts(request);
        return ResponseEntity.ok(new CheckConflictResponse(isPathValid));
    }

}
