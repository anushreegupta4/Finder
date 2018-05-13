import com.th.doctorlocator.FinderConfiguration;
import com.th.doctorlocator.config.github.GithubConfig;
import com.th.doctorlocator.model.User;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = FinderConfiguration.class)
public class FinderConfigurationTest {

  @Autowired
  private User user;

  @Autowired
  private GithubConfig githubConfig;

  @Test
  public void userShouldNotBeNull() {
    assertNotNull(user);
  }

  @Test
  public void githubConfigShouldNotBeNull() {
    assertNotNull(githubConfig);
  }

  @Test
  public void githubConfigOrganisationRepoUrl() {
    assertEquals("", githubConfig.getOrganisationReposUrl(), "https://api.github.com/orgs/%s/repos");
  }

  @Test
  public void githubConfigRepoContributorsUrl() {
    assertEquals("", githubConfig.getRepositoryContributorsUrl(), "https://api.github.com/repos/%s/%s/contributors");
  }
}
