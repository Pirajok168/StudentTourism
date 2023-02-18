//
//  HomeViewModel.swift
//  iosApp
//
//  Created by Данила Еремин on 18.02.2023.
//  Copyright © 2023 orgName. All rights reserved.
//

import Foundation
import shared
import Combine


typealias Collector = Kotlinx_coroutines_coreFlowCollector

class Observer: Collector{
    let callback:(Any?) -> Void
    init(callback: @escaping(Any?) -> Void){
        self.callback = callback
    }
    
    func emit(value: Any?, completionHandler: @escaping (Error?) -> Void) {
        callback(value)
        completionHandler( nil)
    }
}


protocol HomeState{
    var mostPopular: [MostPopular] {  get }
    var loading: Bool { get }
}

protocol EnventHome{
    func getMostPopular()
}
class HomeViewModel: ObservableObject, HomeState{
    @Published private(set) var mostPopular: [MostPopular] = []
    
    @Published private(set) var loading: Bool = false
    
    private let repo: IRepositoryHome = MobileSdk().ISearchDormitories
    
    private lazy var collector: Observer = {
        let collector = Observer {value in
            switch value{
            case let data as FlowResponseSuccess<NSMutableArray>:
                self.mostPopular = data.data as! [MostPopular]
                print(data.data!)
            case let loading as FlowResponseLoading<NSMutableArray>:
                print(loading.isLoading)
            case let error as FlowResponseError<NSMutableArray>:
                print(error.error)
            default:
                print("Неизвестное состояние")
            }
        }
        return collector
    }()
    
    
}

extension HomeViewModel: EnventHome{
    func getMostPopular() {
        repo.getMostPopular { response, eror in
            response?.collect(collector: self.collector, completionHandler: { _ in})
        }
    }
    
    
}

