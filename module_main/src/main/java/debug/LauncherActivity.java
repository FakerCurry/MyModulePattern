package debug;

import android.content.Intent;
import android.os.Bundle;

import com.sjw.lib_common.base.BaseActivity;
import com.sjw.module_main.ui.DemoActivity;
import com.sjw.module_main.ui.WelcomeActivity;

/**
 * Created by pc on 2018/8/1.
 */

public class LauncherActivity extends BaseActivity {
    @Override
    protected void initView() {

    }

    @Override
    protected void judgeNet() {

    }

    @Override
    protected void setLayoutId() {

    }

    @Override
    protected void initData() {

        Bundle bundle = new Bundle();
        bundle.putString("needValue", "LauncherValue");
        startActivity(WelcomeActivity.class, bundle, true);
    }

    @Override
    protected void setListener() {

    }

    @Override
    protected void beforeUnbinder() {

    }
}
