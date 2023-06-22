import SwiftUI
import shared
import Foundation

@UIApplicationMain
class AppDelegate: UIResponder, UIApplicationDelegate {
  var window: UIWindow?

  func application(_ application: UIApplication, didFinishLaunchingWithOptions launchOptions: [UIApplication.LaunchOptionsKey: Any]?) -> Bool {
    HelperKt.doInitKoiniOS()
    window = UIWindow(frame: UIScreen.main.bounds)
    let mainViewController = Main_iosKt.Main()
    window?.rootViewController = mainViewController
    window?.makeKeyAndVisible()
    return true
  }
}

