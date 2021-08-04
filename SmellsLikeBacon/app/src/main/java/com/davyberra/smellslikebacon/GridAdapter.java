package com.davyberra.smellslikebacon;

public class GridAdapter extends RecyclerAdapter {


    private final GridFragment.OnRecipeSelectedInterface listener;

    public GridAdapter(GridFragment.OnRecipeSelectedInterface listener) {
        this.listener = listener;
    }

    @Override
    protected int getLayoutId() {
        return R.layout.grid_item;
    }

    @Override
    protected void onRecipeSelected(int index) {
        listener.onGridRecipeSelected(index);
    }
}
