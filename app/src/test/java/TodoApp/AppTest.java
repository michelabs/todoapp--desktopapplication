/*
 * This Java source file was generated by the Gradle 'init' task.
 */
package TodoApp;

import static junit.framework.Assert.assertNotNull;
import org.junit.Test;

class AppTest {
    @Test void appHasAGreeting() {
        App classUnderTest = new App();
        assertNotNull(classUnderTest.getGreeting(), "app should have a greeting");
    }
}
