package customdata.fileUpload;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.method.annotation.MvcUriComponentsBuilder;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.io.IOException;
import java.util.stream.Collectors;

@Controller
public class FileUploadController {

    private final StorageService service;

    @Autowired
    public FileUploadController(StorageService service){
        this.service = service;
    }

    @GetMapping("/")
    public String listUploadedFiles(Model model) throws IOException {
        model.addAttribute("files", service.loadAll().map(path -> MvcUriComponentsBuilder.fromMethodName(FileUploadController.class,
                "serverFile", path.getFileName().toString()).build().toString())
                .collect(Collectors.toList()));
        return "uploadForm";
    }

    @GetMapping("/files/{fileName:.+}")
    @ResponseBody
    public ResponseEntity<Resource> serverFile(@PathVariable String fileName){
        Resource resource = service.loadAsResource(fileName);
        return ResponseEntity.ok().header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\""+ resource.getFilename() +"\"").body(resource);
    }

    @PostMapping("/")
    public String handleFileUpload(@RequestParam("file")MultipartFile file, RedirectAttributes redirectAttributes){
        service.store(file);
        redirectAttributes.addFlashAttribute("message", "You successfully uploaded "+ file.getOriginalFilename() +"!");
        return "redirect:/";
    }

    @ExceptionHandler(StorageFileNotFoundException.class)
    public ResponseEntity<?> handleStorgeFileNotFound(StorageFileNotFoundException exception){
        return ResponseEntity.notFound().build();
    }
}
