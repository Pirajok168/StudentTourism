//
//  HomeScreen.swift
//  iosApp
//
//  Created by Данила Еремин on 18.02.2023.
//  Copyright © 2023 orgName. All rights reserved.
//

import SwiftUI
struct SamplePopulation: Identifiable, Hashable{
    let id: String = UUID().uuidString
    let image: String
    let score: String
    let title: String
    let city: String
}




struct HomeScreen: View {
    let edgeTop: CGFloat
    let edgeBottom: CGFloat
   

    @State private var searchText = ""
    @State private var offset: CGFloat = .zero
    @State private var showSheet = false
   
    
    @State private var selectedType: TypeNutrition = TypeNutrition.noNutrition()
    @StateObject var navigator: NavigatorFilter = NavigatorFilter()
    @StateObject var homeViewModel: HomeViewModel = HomeViewModel()
    var body: some View {
        ScrollView(.vertical, showsIndicators: false){
            VStack(alignment:.leading){
               
               
                
                
                PopulationVIewer()
                    .environmentObject(homeViewModel)
                    
                
                WhatIsViewViewer()
        
            }
            .onAppear{
                print("вызвали")
                homeViewModel.getMostPopular()
            }
            .padding(.top, edgeTop + 120)
            .padding(.bottom, edgeBottom)
            .frame(maxWidth: .infinity, alignment: .leading)
            .background{
                GeometryReader{ proxy -> Color in
                    DispatchQueue.main.async {
                        self.offset = proxy.frame(in: .global).minY
                    }
                    return Color.clear
                }
            }
            .sheet(isPresented: $showSheet, content: {
                FilterScreen(selectedType: $selectedType)
                    .environmentObject(navigator)
            })
            
        }
        .overlay{
            ZStack{
                Color.clear.background(.ultraThinMaterial)
                //MARK: LARGE TOP BAR
               
                VStack{
                    Text("Найди своё")
                        .font(.title2)
                        .fontWeight(.thin)
                        .offset(y: 5)
                    
                    Text("ПУТЕШЕСТВИЕ")
                        .font(.title.bold())
                    
                    
                    HStack(alignment: .center){
                        Image(systemName: "magnifyingglass")
                        TextField(text: $searchText, label: {
                            Text("Поиск")
                        })
                        .textFieldStyle(.roundedBorder)
                        
                        Button(action: {
                            showSheet = true
                        }, label: {
                            Image(systemName: "slider.horizontal.3")
                        })
                        .buttonStyle(.plain)
                        
                    }
                    
                }
                .padding(.horizontal)
                .frame(height:  edgeTop + 120 ,alignment: .bottom)
                .padding(.bottom)
               
                
            }
            .frame(height: edgeTop + 120  ,alignment: .bottom)
            
            .frame(maxWidth: .infinity, maxHeight: .infinity, alignment: .top)

        }
    }
}

struct HomeScreen_Previews: PreviewProvider {
    static var previews: some View {
        GeometryReader{ proxy in
            let edgeTop = proxy.safeAreaInsets.top
            let edgeBottom = proxy.safeAreaInsets.bottom
            HomeScreen(edgeTop: edgeTop, edgeBottom: edgeBottom)
                .ignoresSafeArea()
            
        }
       
    }
}


