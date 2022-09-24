package StepDefinition;

import Core.Engine.Base;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;

public class BaseStepDef {

    @Given("i hit api with {string} number page")
    public void iHitApiWithNumberPage(String numberPage) {
        Base.getResponseBody(numberPage);
    }

    @Then("i verify {string} of data json returned")
    public void iVerifyOfDataJsonReturned(String numberPage) {
        Base.assertResponseBody(numberPage);
    }

    @Given("i hit api {string}")
    public void iHitApi(String url) {
        Base.hitUrl(url);
    }

    @Then("i validate schema is correct")
    public void iValidateSchemaIsCorrect() {
        Base.checkSchema();
    }
}
