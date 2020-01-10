package aemexamples.core.models;

import org.apache.sling.api.resource.Resource;
import org.apache.sling.models.annotations.Model;
import org.apache.sling.api.SlingHttpServletRequest;
import org.apache.sling.models.annotations.injectorspecific.SlingObject;
import org.apache.sling.models.annotations.DefaultInjectionStrategy;
import javax.inject.Inject;
import java.util.Random;

@Model(adaptables = {SlingHttpServletRequest.class, Resource.class}, defaultInjectionStrategy = DefaultInjectionStrategy.OPTIONAL)
public class SSIModel {
	@SlingObject
	private SlingHttpServletRequest slingHttpServletRequest;

	@Inject
	private Resource resource;

	public String getRandomNumber() {
		final Random rand = new Random();

		return Integer.toString(rand.nextInt(1000));
	}

    public boolean isRenderComponent() {
		// If the request resource path equals this components resource path then the request was for this component specifically.
		// This helps the HTL template properly render either the SSI or the parsys.

		return slingHttpServletRequest.getPathInfo().startsWith(resource.getPath());
	}

	public String getSsiIncludePath() {
		return resource.getPath() + ".html";
	}
}
