/*
The MIT License (MIT)

Copyright (c) 2016 Glauber Lima

Permission is hereby granted, free of charge, to any person obtaining a copy
of this software and associated documentation files (the "Software"), to deal
in the Software without restriction, including without limitation the rights
to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
copies of the Software, and to permit persons to whom the Software is
furnished to do so, subject to the following conditions:

The above copyright notice and this permission notice shall be included in all
copies or substantial portions of the Software.

THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE
SOFTWARE.
 */
package br.com.glauber.android.xposed.meutim_rootcheckingremover;

import de.robv.android.xposed.IXposedHookLoadPackage;
import de.robv.android.xposed.XC_MethodReplacement;
import de.robv.android.xposed.XposedHelpers;
import de.robv.android.xposed.callbacks.XC_LoadPackage;

public class XposedModule implements IXposedHookLoadPackage {

    private static final String PACKAGE_NAME = "br.com.timbrasil.meutim";
    private static final String CLASS_NAME = "com.accenture.meutim.activities.SplashScreen";
    private static final String METHOD_NAME = "r";
    private static final String VARIABLE_NAME = "g";

    @Override
    public void handleLoadPackage(XC_LoadPackage.LoadPackageParam loadPackageParam) throws Throwable {

        if (!loadPackageParam.packageName.equals(PACKAGE_NAME))
            return;

        XposedHelpers.findAndHookMethod(
                CLASS_NAME,
                loadPackageParam.classLoader,
                METHOD_NAME,
                new XC_MethodReplacement() {
                    @Override
                    protected Object replaceHookedMethod(MethodHookParam methodHookParam) throws Throwable {
                        XposedHelpers.setObjectField(methodHookParam.thisObject, VARIABLE_NAME, false);
                        return XC_MethodReplacement.DO_NOTHING;
                    }
                });

    }
}