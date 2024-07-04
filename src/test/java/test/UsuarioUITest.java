package test;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.concurrent.TimeUnit;

import static org.junit.jupiter.api.Assertions.assertTrue;

public class UsuarioUITest {

    private WebDriver driver;

    @BeforeEach
    public void setUp() {
        // Configurar la ruta al WebDriver
        System.setProperty("webdriver.edge.driver",
                "C:\\ADSO_SENA\\WebDriver\\msedgedriver.exe");

        // Inicializar el WebDriver
        driver = new EdgeDriver();
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);

        // Navegar a la URL de la aplicación
        driver.get("http://localhost:8080/SipressJavaApplication/index.jsp");
    }

//    @AfterEach
//    public void tearDown() {
//        // Cerrar el navegador después de cada prueba
//        if (driver != null) {
//            driver.quit();
//        }
//    }

    @Test
    public void testAgregarUsuarioButton() {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(30));

        // Encontrar y hacer clic en el enlace de la sección de usuarios en el index
        WebElement usuariosLink = wait.until(
                ExpectedConditions.elementToBeClickable(By.xpath(
                        "//a[@href='ServletUsuario']")));
        usuariosLink.click();

        // Esperar a que la página de usuarios se cargue completamente
        wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath(
                "//a[contains(@class, 'btn-success')]")));

        // Encontrar y hacer clic en el botón "Agregar Registro"
        WebElement agregarRegistroButton = driver.findElement(By.xpath(
                "//a[contains(@class, 'btn-success')]"));
        agregarRegistroButton.click();

        // Esperar a que el modal se muestre
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.id(
                "registrarUsuarioModal")));

        // Verificar que el modal de registro se muestra
        WebElement registrarUsuarioModal = driver.findElement(By.id(
                "registrarUsuarioModal"));
        assertTrue(registrarUsuarioModal.isDisplayed(),
                "El modal de registro de usuario no se mostró correctamente.");
    }
}







