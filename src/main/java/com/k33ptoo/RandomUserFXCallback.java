package com.k33ptoo;

import java.util.List;

/**
 *
 * @author KeepToo
 */
public interface RandomUserFXCallback {

    void onSuccess(List<RandomUserFXModel> data);

    void onError(String err);
}
