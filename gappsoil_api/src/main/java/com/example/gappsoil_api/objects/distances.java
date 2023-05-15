package com.example.gappsoil_api.objects;

public class distances {
    long repoId;

    double distance;

    public distances(long repoId, double distance) {
        this.repoId = repoId;
        this.distance = distance;
    }



    public long getRepoId() {
        return repoId;
    }

    public void setRepoId(long repoId) {
        this.repoId = repoId;
    }

    public double getDistance() {
        return distance;
    }

    public void setDistance(double distance) {
        this.distance = distance;
    }


    public void setDistance(Float distance) {
        this.distance = distance;
    }
}
