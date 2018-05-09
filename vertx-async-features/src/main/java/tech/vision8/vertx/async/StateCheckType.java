package tech.vision8.vertx.async;

/**
 * The coverage level to be used when checking the state of the app.
 * @author vision8
 */
public enum StateCheckType {

    /** Blocking way */
    B,

    /** NonBlocking way */
    NB,

    /** using AsyncResult Handler */
    ARH,

    /** using Future */
    F,

    /** using the event bus, option 1: one verticle of type non worker (standard) **/
    EB_1NW,

    /** using event bus, option 2: two verticles of type non worker (standard) */
    EB_2NW,

    /** using event bus, option 3: two verticles of type worker */
    EB_2W

}
