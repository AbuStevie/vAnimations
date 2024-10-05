import android.widget.ImageView;

public class Vanimate {

    // uses rotation() to shake the image
    public void shakeImage(final ImageView image, final int angle, final int times, final int duration) {

        if (times == 1) {
            image.animate().rotation(0).setDuration(duration); // last iteration return to 0
            return;
        }

        image.animate().rotation(angle).setDuration(duration).withEndAction(new Runnable() { // rotate
            @Override
            public void run() {
                shakeImage(image, -angle, times - 1, duration);} // recursion
        });
    }

    // fast shake
    public void shakeImage(ImageView image) {
        shakeImage(image, 2, 7, 50);
    }

    // uses alpha() to flicker the image
    // depth 0.5 will not flicker.
    // depth 1.0 will flicker to full opacity
    public void flickerImage(final ImageView image, final float depth, final int times, final int duration) {
        if (times == 1) {
            image.animate().alpha(1f).setDuration(duration); // return to normal
            return; // No twinkling required
        }

        image.animate().alpha(depth).setDuration(duration).withEndAction(new Runnable() { // fade
            @Override
            public void run() {
                flickerImage(image, 1 - depth, times - 1, duration); // recursion

            }
        });
    }
    // fast flicker
    public void flickerImage(ImageView image) {
        flickerImage(image, 0.75f, 7, 35);
    }

    // uses translationX() and translationY() to zigzag the image
    // depth value in dp
    // - depth to translation Y changes direction
    public void zigzagImage(final ImageView image, final int depth, final int times, final int duration) {
        if (times == 1) {
            image.animate().translationX(0).translationY(0).setDuration(duration); // return to normal
            return; // No zigzag required
        }

        image.animate().translationX(depth).translationY(-depth).setDuration(duration).withEndAction(new Runnable() { // zig
            @Override
            public void run() {
                zigzagImage(image, - depth, times - 1, Math.round(duration * 0.75f) ); // recursion zag make it faster

            }
        });
    }

    // fast zigzag
    public void zigzagImage(ImageView image) {
        zigzagImage(image, 8, 9, 80);
    }

    // uses scaleX() and scaleY() to twinkle the image
    // motion is "padded" - slowed and minimized
    public void twinkleImage(final ImageView image, final float depth, final int times, final int duration) {
        if (times == 1) {
            image.animate().scaleX(1f).scaleY(1f).setDuration(duration); // return to normal
            return; //
        }

        image.animate().scaleX(depth).scaleY(depth).setDuration(duration).withEndAction(new Runnable() { // shrink
            @Override
            public void run() {
                image.animate().scaleX(1f).scaleY(1f).setDuration(duration).withEndAction(new Runnable() { // return to normal
                    @Override
                    public void run() {
                        twinkleImage(image,  depth + (1-depth)/2, times - 1, (int) Math.round(1.25 * duration)); // recursion smaller and slower
                    }
                });

            }
        });
    }

    // nice twinkle effect
    public void twinkleImage(ImageView image) {
        twinkleImage(image, 0.8f, 7, 50);
    }

}
