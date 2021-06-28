package jp.co.cyberagent.katalog.compose

import android.view.Window
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.graphics.toArgb
import androidx.compose.ui.platform.LocalView
import androidx.core.view.WindowInsetsControllerCompat
import androidx.fragment.app.FragmentManager
import androidx.lifecycle.viewmodel.compose.viewModel
import jp.co.cyberagent.katalog.compose.page.DiscoveryPage
import jp.co.cyberagent.katalog.compose.page.PreviewPage
import jp.co.cyberagent.katalog.compose.res.materialColors
import jp.co.cyberagent.katalog.compose.util.FragmentManagerProvider
import jp.co.cyberagent.katalog.compose.widget.ModalVisibility

@Composable
internal fun App(
    window: Window,
    fragmentManager: FragmentManager,
    viewModel: KatalogViewModel = viewModel()
) {
    val darkTheme = isSystemInDarkTheme()

    FragmentManagerProvider(fragmentManager) {
        MaterialTheme(
            colors = materialColors(darkTheme)
        ) {
            AppWindow(
                window = window,
                darkTheme = darkTheme
            )
            MainContent(
                viewModel = viewModel
            )
        }
    }
}

@Composable
private fun AppWindow(
    window: Window,
    darkTheme: Boolean
) {
    val view = LocalView.current
    window.statusBarColor = MaterialTheme.colors.surface.toArgb()
    val controller = WindowInsetsControllerCompat(window, view)
    controller.isAppearanceLightNavigationBars = !darkTheme
    controller.isAppearanceLightStatusBars = !darkTheme
}

@Composable
private fun MainContent(viewModel: KatalogViewModel) {
    val selectedComponent by viewModel.selectedComponent.collectAsState()

    DiscoveryPage(
        viewModel = viewModel
    )
    ModalVisibility(
        value = selectedComponent
    ) {
        PreviewPage(
            viewModel = viewModel,
            component = it
        )
    }
}
