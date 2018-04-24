package de.pawlidi.ipcamview.android.activity;

import android.app.Dialog;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.NavigationView;
import android.support.design.widget.Snackbar;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;

import com.mobsandgeeks.saripaar.ValidationError;
import com.path.android.jobqueue.JobManager;

import org.greenrobot.eventbus.EventBus;

import java.util.List;

import javax.inject.Inject;

import de.pawlidi.ipcamview.android.R;
import de.pawlidi.ipcamview.android.model.Model;
import de.pawlidi.ipcamview.android.utils.Log;
import de.pawlidi.ipcamview.android.utils.ViewUtils;

/**
 * Main activity for the IPCamView
 *
 * @author pawlidim
 */
public class MainActivity extends BaseActivity implements NavigationView.OnNavigationItemSelectedListener {

    @Inject
    Model model;

    @Inject
    EventBus eventBus;

    @Inject
    JobManager jobManager;

    private DrawerLayout rootView;
    private FloatingActionButton fab;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getComponent().inject(this);
        setContentView(R.layout.activity_main);

        rootView = (DrawerLayout) findViewById(R.id.rootView);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, rootView, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        rootView.setDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);
        requestAppPermissions(rootView);

        fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });

        Log.info("Call on start main activity try to register this as even observer");
        eventBus.register(this);
    }

    @Override
    protected void onDestroy() {
        Log.info("Call on destroy main activity");
        eventBus.unregister(this);
        jobManager.stop();
        super.onDestroy();
    }

    @Override
    public void onValidationSucceeded() {
        Log.info("All input values valid......");
    }

    @Override
    public void onValidationFailed(List<ValidationError> errors) {
        for (ValidationError error : errors) {
            View view = error.getView();
            String message = error.getCollatedErrorMessage(this);

            // Display error messages ;)
            if (view instanceof EditText) {
                ((EditText) view).setError(message);
            } else {
                ViewUtils.createToast(this, message);
            }
        }
    }

    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        Log.info("Handle navigation view item clicks....");
        int id = item.getItemId();
        if (id == R.id.nav_settings) {
            Log.info("Try to open dialog for settings");
        } else if (id == R.id.nav_share) {
            Dialog dialog = createDialog(R.layout.share_dialog, R.string.message_share);
            dialog.show();
            Log.info("Try to open dialog for share");
        } else if (id == R.id.nav_send) {
            showRateDialog();
            Log.info("Try to open dialog for rate");
        } else if (id == R.id.nav_credits) {
            Dialog dialog = createDialog(R.layout.send_dialog, R.string.message_credits);
            dialog.show();
            Log.info("Try to open dialog for credits");
        }

        rootView.closeDrawer(GravityCompat.START);
        return true;
    }

    private Dialog createDialog(int dialogId, int titleId) {
        Dialog dialog = new Dialog(MainActivity.this);
        dialog.setContentView(dialogId);
        dialog.setCancelable(true);
        dialog.setCanceledOnTouchOutside(true);
        dialog.setTitle(getString(titleId));
        return dialog;
    }

    @Override
    public void onBackPressed() {
        if (rootView.isDrawerOpen(GravityCompat.START)) {
            rootView.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }
}
