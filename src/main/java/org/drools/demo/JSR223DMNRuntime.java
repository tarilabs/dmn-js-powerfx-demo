package org.drools.demo;

import java.util.Arrays;

import javax.inject.Singleton;

import org.drools.core.io.impl.ClassPathResource;
import org.kie.dmn.api.core.DMNRuntime;
import org.kie.dmn.core.internal.utils.DMNRuntimeBuilder;
import org.kie.dmn.core.jsr223.JSR223EvaluatorCompilerFactory;
import org.kie.kogito.decision.DecisionModel;
import org.kie.kogito.decision.DecisionModels;
import org.kie.kogito.dmn.DmnDecisionModel;

@Singleton
public class JSR223DMNRuntime implements DecisionModels {
    
    private static final DMNRuntime DMNRUNTIME;
    static {
        ClassPathResource bmiJS = new ClassPathResource("/Nashorn/BMI.dmn", JSR223DMNRuntime.class);
        ClassPathResource bmiPowerFx = new ClassPathResource("/PowerFx/BMI.dmn", JSR223DMNRuntime.class);
        DMNRUNTIME = DMNRuntimeBuilder.fromDefaults()
            .setDecisionLogicCompilerFactory(new JSR223EvaluatorCompilerFactory())
            .buildConfiguration()
            .fromResources(Arrays.asList(bmiJS, bmiPowerFx))
            .getOrElseThrow(e -> new RuntimeException("Error initializing DMNRuntime", e));
    }

    @Override
    public DecisionModel getDecisionModel(String namespace, String name) {
        return new DmnDecisionModel(DMNRUNTIME, namespace, name);
    }
}
