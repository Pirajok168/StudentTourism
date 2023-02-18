import SwiftUI
import shared

@main
struct iOSApp: App {
    init(){
        MobileSdk().doInit(configuration: CorePlatformConfiguration())
    }
	var body: some Scene {
		WindowGroup {
            GeometryReader{ proxy in
                let edgeTop = proxy.safeAreaInsets.top
                let edgeBottom = proxy.safeAreaInsets.bottom
                HomeScreen(edgeTop: edgeTop, edgeBottom: edgeBottom)
                    .ignoresSafeArea()
            }
		}
	}
}
