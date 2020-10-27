package com.larsonapps.personalcookbook.widget;

import android.appwidget.AppWidgetManager;
import android.appwidget.AppWidgetProvider;
import android.content.Context;
import android.content.Intent;
import android.widget.RemoteViews;

import com.larsonapps.personalcookbook.R;

/**
 * Implementation of App Widget functionality.
 */
public class CookbookAppWidget extends AppWidgetProvider {

    /**
     * Method to update all baking widgets
     * @param context to use
     * @param appWidgetManager to use
     * @param appWidgetId of all widgets
     */
    static void updateAppWidget(Context context, AppWidgetManager appWidgetManager,
                                int appWidgetId) {
        // Construct the RemoteViews object
        RemoteViews views = new RemoteViews(context.getPackageName(), R.layout.cookbook_app_widget);
        // create the intent
        Intent intent = new Intent(context, StackWidgetService.class);
        // add the app widget id
        intent.putExtra(AppWidgetManager.EXTRA_APPWIDGET_ID, appWidgetId);
        // set remote adapter to stack view
        views.setRemoteAdapter(R.id.widget_stack_view, intent);
        // set empty view
        views.setEmptyView(R.id.widget_stack_view, R.id.widget_empty_text_view);

        // Instruct the widget manager to update the widget
        appWidgetManager.updateAppWidget(appWidgetId, views);
    }

    @Override
    public void onUpdate(Context context, AppWidgetManager appWidgetManager, int[] appWidgetIds) {
        // There may be multiple widgets active, so update all of them
        for (int appWidgetId : appWidgetIds) {
            updateAppWidget(context, appWidgetManager, appWidgetId);
        }
    }

    @Override
    public void onEnabled(Context context) {
        // Enter relevant functionality for when the first widget is created
    }

    @Override
    public void onDisabled(Context context) {
        // Enter relevant functionality for when the last widget is disabled
    }
}

