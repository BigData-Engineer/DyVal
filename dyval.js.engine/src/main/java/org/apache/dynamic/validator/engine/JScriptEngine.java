package org.apache.dynamic.validator.engine;

import javax.script.ScriptEngine;

import org.apache.dynamic.validator.engine.exception.JSEngineException;

public interface JScriptEngine {

    public ScriptEngine getEngine() throws JSEngineException;
    public Object invokeFunction(String functionName) throws JSEngineException;
    
}
