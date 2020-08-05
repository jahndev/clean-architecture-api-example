package co.com.taskmanagement.infrastructure.application;

import lombok.NoArgsConstructor;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;

@NoArgsConstructor(staticName = "of")
public class URIManager {

  public URI createURIFromPath(String path, Object... params) {
    return ServletUriComponentsBuilder.fromCurrentRequest().path(path)
        .buildAndExpand(params).toUri();
  }

}
